package es.wacoco.csvfilteringandautomatedsearch;

import es.wacoco.csvfilteringandautomatedsearch.Camel.database.Database;
import es.wacoco.csvfilteringandautomatedsearch.Camel.processor.CreateJobProcessor;
import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import es.wacoco.csvfilteringandautomatedsearch.model.Job;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class CreateJobProcessorTest {
    private CreateJobProcessor processor;
    @Mock
    private Database databaseMock;
    @Mock
    private Exchange exchangeMock;
    @Mock
    private Message inMessageMock;

    @Before
    public void setIp() {
        MockitoAnnotations.initMocks(this);
        processor = new CreateJobProcessor();
        processor.database = databaseMock;

        when(exchangeMock.getIn()).thenReturn(inMessageMock);
    }

    @Test
    public void testProcess() throws Exception {
        List<Company> companies = new ArrayList<>();
        when(inMessageMock.getBody(List.class)).thenReturn(companies);

        processor.process(exchangeMock);

        verify(databaseMock, times(1)).createJob(any(Job.class));
        verify(inMessageMock, times(1)).setBody(any(Job.class));
    }

}
