package com.comelystreet.services.mongo;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comelystreet.constants.TimeConstants;
import com.comelystreet.dao.mongodb.StoreDailySchedulingDetailRepo;
import com.comelystreet.dao.mongodb.model.ServiceItemDetail;
import com.comelystreet.dao.mongodb.model.StoreDailyScheduleDetail;
import com.comelystreet.dao.mongodb.model.StoreDetail;
import com.comelystreet.dao.mongodb.model.StoreOperationalTimeDetail;
import com.comelystreet.dao.mongodb.model.TimeSlotDetail;
import com.comelystreet.services.util.DateTimeUtility;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@Service
public class StoreDailyScheduleDetailService {

    @Autowired
    private StoreDailySchedulingDetailRepo clientDailySchedulingDetailRepo;

    @Autowired
    private StoreDetailService clientDetailServiceMongo;

    public void saveOrUpdateInTransaction(final StoreDailyScheduleDetail clientDailySchedulingDetailDataModel) {
        clientDailySchedulingDetailRepo.save(clientDailySchedulingDetailDataModel);
    }

    public LinkedHashSet<Long> findTimeSlotsForStore(final String storeId, final String date,
            final List<ServiceItemDetail> serviceItemDataModels) {
        final LinkedHashSet<Long> timeSlots = Sets.newLinkedHashSet();
        try {
            long totalTimeRequired = 0;
            // calculate the time required for services
            for (ServiceItemDetail item : serviceItemDataModels) {
                totalTimeRequired += item.getTime();
            }

            StoreDetail clientDataModel = clientDetailServiceMongo.findByStoreId(storeId);
            if (null == clientDataModel) {
                return timeSlots;
            }

            // get the time slots for client
            List<StoreDailyScheduleDetail> clientDailySchedulingDetailDataModels = clientDailySchedulingDetailRepo
                    .findByStoreDetail_IdAndScheduleDate(storeId,
                            Long.valueOf(DateTimeUtility.convertFromUIDateStringToDBDateString(date)));

            if (clientDailySchedulingDetailDataModels == null || clientDailySchedulingDetailDataModels.isEmpty()) {
                return timeSlots;
            }
            StoreDailyScheduleDetail clientDailySchedulingDetailDataModel = clientDailySchedulingDetailDataModels
                    .get(0);
            HashSet<TimeSlotDetail> allClientTimeSlots = clientDailySchedulingDetailDataModel.getTimeSlotDetails();

            LinkedList<TimeSlotDetail> availabilityDataModels = Lists.newLinkedList(allClientTimeSlots);

            LinkedHashMap<Long, Integer> avalaibleSlotsMap = Maps.newLinkedHashMap();
            System.out.println("Slots" + availabilityDataModels);
            for (int i = 0; i < availabilityDataModels.size(); i++) {

                TimeSlotDetail keySlot = availabilityDataModels.get(i);
                avalaibleSlotsMap.put(keySlot.getTimeSlot(), 0);
                for (int j = i; j < availabilityDataModels.size(); j++) {
                    TimeSlotDetail t = availabilityDataModels.get(j);

                    int slotAvailable = 0;
                    if (t.getSlotsAvailable() != 0) {
                        slotAvailable = 1;
                    }
                    avalaibleSlotsMap.put(keySlot.getTimeSlot(), avalaibleSlotsMap.get(keySlot.getTimeSlot())
                            + slotAvailable);

                    if (t.getSlotsAvailable() == 0)
                        break;
                }
            }
            System.out.println("Map" + avalaibleSlotsMap);
            System.out.println("TotalTime" + totalTimeRequired);
            long toSkip = skipTime(date);
            for (Map.Entry<Long, Integer> entry : avalaibleSlotsMap.entrySet()) {
                if ((toSkip > 0) && (toSkip > (entry.getKey().longValue()))) {
                    // SKIP BASED ON CURRENT TIME
                    continue;
                }
                if (entry.getValue().longValue() * TimeConstants.TIME_SLOT_GAP >= totalTimeRequired) {
                    timeSlots.add(entry.getKey());
                }
            }
        } catch (Exception e) {
            System.out.println("errorrrrrr");
            e.printStackTrace();
        }
        System.out.println(timeSlots);
        return timeSlots;
    }

    private Long skipTime(final String date) throws ParseException {
        long toSkip = 0;
        if (DateTimeUtility.convertFromUIDateStringToDBDateString(date).equals(
                DateTimeUtility.getCurrentDateAsDBDateString())) {
            // Add 60 minutes to current time
            String skipTime = DateTimeUtility.addMinutesToCurrentTime(60);
            System.out.println("skipTime: " + skipTime);
            toSkip = Long.valueOf(skipTime);
            System.out.println("ToSKip: " + toSkip);
        }
        return toSkip;
    }

    public StoreDailyScheduleDetail mockDailyStoreScheduling(@NotNull final StoreDetail clientDataModel,
            @NotNull final Date date) {

        StoreDailyScheduleDetail clientDailySchedulingDataModel = new StoreDailyScheduleDetail();
        clientDailySchedulingDataModel.setStoreDetail(clientDataModel);
        clientDailySchedulingDataModel.setScheduleDate(Long.valueOf(DateTimeUtility.getStringFromDate(date,
                DateTimeUtility.YYYYMMDD)));

        final List<TimeSlotDetail> timeSlotDataModels = Lists.newArrayList();

        StoreOperationalTimeDetail clientOperationalTimeDataModel = clientDataModel.getOperationalTimeDetail();
        long closeTime = clientOperationalTimeDataModel.getCloseTime();

        for (long startTime = clientOperationalTimeDataModel.getOpenTime(); startTime < closeTime;) {

            System.out.println(">>>>>>" + startTime);
            timeSlotDataModels.add(new TimeSlotDetail(startTime, 2));

            if ((((startTime % 100) + TimeConstants.TIME_SLOT_GAP) % 60) == 0) {
                startTime = startTime - (startTime % 100) + 100;
            } else {
                startTime = startTime + TimeConstants.TIME_SLOT_GAP;
            }
            System.out.println("<<<<<<<" + startTime);

        }
        System.out.println(timeSlotDataModels);
        clientDailySchedulingDataModel.setTimeSlotDetails(new LinkedHashSet<TimeSlotDetail>(timeSlotDataModels));

        clientDailySchedulingDetailRepo.save(clientDailySchedulingDataModel);

        return clientDailySchedulingDataModel;

    }

    public Map<Long, String> getTimeSlotsAsStringMap(final String clientId, final String date,
            List<ServiceItemDetail> serviceItemDataModels) {
        LinkedHashSet<Long> availableTimeSlots = findTimeSlotsForStore(clientId, date, serviceItemDataModels);
        final LinkedHashMap<Long, String> availableTimeSlotsMap = Maps.newLinkedHashMap();

        availableTimeSlots.stream().forEach(x -> {
            availableTimeSlotsMap.put(x, DateTimeUtility.getTimeFromLong(x));
        });
        return availableTimeSlotsMap;
    }

}
