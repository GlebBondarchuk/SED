package com.bsu.sed.controller.admin;

import com.bsu.sed.model.BackgroundProcessKey;
import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.dto.BackgroundProcessDto;
import com.bsu.sed.service.schedule.ScheduleService;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.text.ParseException;
import java.util.List;

/**
 * @author gbondarchuk
 */
@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequestMapping("/admin/processes")
public class BackgroundProcessController {

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping("")
    public ModelAndView getProcessesPage() throws ParseException, SchedulerException {
        List<BackgroundProcessDto> processes = scheduleService.find();
        ModelAndView modelAndView = new ModelAndView(Tiles.BACKGROUND_PROCESSES_PAGE.getTileName());
        modelAndView.addObject("processes", processes);
        return modelAndView;
    }

    @RequestMapping("/start/{processKey}")
    public RedirectView startProcess(@PathVariable("processKey") BackgroundProcessKey processKey) throws SchedulerException {
        scheduleService.startNow(processKey);
        return new RedirectView("/admin/processes", true);
    }

    @RequestMapping("/disable/{processKey}")
    public RedirectView disableProcess(@PathVariable("processKey") BackgroundProcessKey processKey) throws SchedulerException {
        scheduleService.disableProcess(processKey);
        return new RedirectView("/admin/processes", true);
    }

    @RequestMapping("/enable/{processKey}")
    public RedirectView enableProcess(@PathVariable("processKey") BackgroundProcessKey processKey) throws SchedulerException {
        scheduleService.enableProcess(processKey);
        return new RedirectView("/admin/processes", true);
    }

    @RequestMapping("/reschedule/{processKey}/{cron}")
    public RedirectView rescheduleProcess(@PathVariable("processKey") BackgroundProcessKey processKey,
                                          @PathVariable("cron") String cron) throws SchedulerException, ParseException {
        if (!CronExpression.isValidExpression(cron)) {
            throw new RuntimeException("Cron Expression is invalid");
        }
        scheduleService.rescheduleProcess(processKey, cron);
        return new RedirectView("/admin/processes", true);
    }
}
