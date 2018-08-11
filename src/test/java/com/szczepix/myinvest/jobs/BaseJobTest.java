package com.szczepix.myinvest.jobs;

import org.junit.Test;

import static org.junit.Assert.*;

public class BaseJobTest {

    private BaseJobMock baseJob;

    @Test
    public void submit() throws Exception {
        baseJob = new BaseJobMock(1);
        assertNotNull(baseJob.submit());
        assertEquals(baseJob.counter, 1);
    }

    @Test
    public void isReadyWhenIsOne() {
        baseJob = new BaseJobMock(1);
        assertTrue(baseJob.isReady());
        assertTrue(baseJob.isReady());
    }

    @Test
    public void isReadyWhenIsMore() {
        baseJob = new BaseJobMock(3);
        assertFalse(baseJob.isReady());
        assertFalse(baseJob.isReady());
        assertTrue(baseJob.isReady());
        assertFalse(baseJob.isReady());
    }

    @Test
    public void submitWithCallback() throws Exception {
        baseJob = new BaseJobMock(this, "", 1);
        assertNotNull(baseJob.submit());
        assertEquals(baseJob.counter, 1);
    }

    @Test
    public void isReadyWhenIsOneAndWithCallback() {
        baseJob = new BaseJobMock(this, "", 1);
        assertTrue(baseJob.isReady());
        assertTrue(baseJob.isReady());
    }

    @Test
    public void isReadyWhenIsMoreAndWithCallback() {
        baseJob = new BaseJobMock(this, "", 3);
        assertFalse(baseJob.isReady());
        assertFalse(baseJob.isReady());
        assertTrue(baseJob.isReady());
        assertFalse(baseJob.isReady());
    }
}