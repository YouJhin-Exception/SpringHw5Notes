package org.youjhin.springhw5notes.controllers;


import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
public class CustomMetricsController {

    private final MeterRegistry meterRegistry;

    @Autowired
    public CustomMetricsController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    //наша отдельная страница с выбранными метриками
    @GetMapping("/metrics-page")
    public String getMetricsPage(Model model) {
        Double jvmMemoryUsed = Objects.requireNonNull(meterRegistry.find("jvm.memory.used").gauge()).value();
        Long httpServerRequests = Objects.requireNonNull(meterRegistry.find("http.server.requests").timer()).count();
        Double getAllNotesCounterValue = Objects.requireNonNull(meterRegistry.find("getAllNotesCounter").counter()).count();

        model.addAttribute("jvmMemoryUsed", jvmMemoryUsed);
        model.addAttribute("httpServerRequests", httpServerRequests);
        model.addAttribute("getAllNotesCounter", getAllNotesCounterValue);

        return "metrics";
    }

}
