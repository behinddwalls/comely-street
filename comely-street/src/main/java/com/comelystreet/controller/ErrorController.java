package com.comelystreet.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author behinddwalls
 *
 */
@Controller
@RequestMapping("/error")
public class ErrorController extends BaseController {

    @RequestMapping(value = { "", "500" })
    public ModelAndView send500InternalServerError(final Model model, final HttpServletResponse httpServletResponse) {
        httpServletResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        return getModelAndView("error/500", model);
    }

    @RequestMapping(value = { "503" })
    public ModelAndView send503InternalServerError(final Model model, final HttpServletResponse httpServletResponse) {
        httpServletResponse.setStatus(HttpStatus.SC_METHOD_NOT_ALLOWED);
        return getModelAndView("error/503", model);
    }

    @RequestMapping(value = { "400" })
    public ModelAndView send400InternalServerError(final Model model, final HttpServletResponse httpServletResponse) {
        httpServletResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
        return getModelAndView("error/400", model);
    }

    @RequestMapping("401")
    public ModelAndView send401UnAuthorizedError(final Model model, final HttpServletResponse httpServletResponse) {
        httpServletResponse.setStatus(HttpStatus.SC_UNAUTHORIZED);
        return getModelAndView("error/401", model);
    }

    @RequestMapping("403")
    public ModelAndView send403AccessDeniedError(final Model model, final HttpServletResponse httpServletResponse) {
        httpServletResponse.setStatus(HttpStatus.SC_FORBIDDEN);
        return getModelAndView("error/403", model);
    }

    @RequestMapping("404")
    public ModelAndView send404PageNotFoundError(final Model model, final HttpServletResponse httpServletResponse) {
        httpServletResponse.setStatus(HttpStatus.SC_NOT_FOUND);
        return getModelAndView("error/404", model);
    }
}
