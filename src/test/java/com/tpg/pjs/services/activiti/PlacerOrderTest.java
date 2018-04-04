package com.tpg.pjs.services.activiti;

import com.tpg.pjs.ordering.Order;
import com.tpg.pjs.ordering.OrderFixture;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:processes/ordering.bpmn20.xml"})
public class PlacerOrderTest implements OrderFixture {

    @Test
    @Deployment
    public void deploy() {

        Order order = newOrder();

        Map<String, Object> mapping = new HashMap<>();
        mapping.put("newOrder", order);

        runtimeService.startProcessInstanceById("placeOrder", mapping);

        List<Task> actual = taskService.createTaskQuery().list();

        Optional<Task> placeOnOrderQueueTask = actual.stream().filter(task -> task.getName().equals("placeOnOrderQueue")).findFirst();
        assertTrue(placeOnOrderQueueTask.isPresent());
    }

    @Configuration
    static class Config {

        @Bean
        public ActivitiRule activitiRule() {

            ActivitiRule rule = new ActivitiRule();

            rule.setProcessEngine(processEngine());
            return rule;
        }

        @Bean
        public ProcessEngine processEngine() {

            return processEngine;
        }

        @Autowired
        private ProcessEngine processEngine;
    }

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    @Rule
    public ActivitiRule activitiRule;
}
