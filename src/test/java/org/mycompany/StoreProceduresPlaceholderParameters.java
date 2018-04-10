package org.mycompany;

import java.util.Collections;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class StoreProceduresPlaceholderParameters extends CamelSpringTestSupport {

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("spring/camel-context.xml");
    }
    
    @Test
    public void test() throws InterruptedException {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMessageCount(1);
        mock.expectedBodyReceived().body().isEqualTo(true);
        
        template.sendBody("direct:sp-test", Collections.singletonMap("number", 7));
        
        assertMockEndpointsSatisfied();
    }

}
