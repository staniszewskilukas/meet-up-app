package pl.sda.meetup.myownmeetup.date_and_time;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import java.util.Map;

import static org.junit.Assert.*;

public class TimeUtilTest {


    TimeUtil timeUtil;

    @Before
    public void createObjects(){
        timeUtil = new TimeUtil();
    }


    @Test
    public void determineTimeWhenEventPassed() {
        //given
        LocalDate from = LocalDate.now().minusDays(20);
        LocalDate to = LocalDate.now().minusDays(10);
        //when
        String determineTime = timeUtil.determineTime(from, to);
        //then
        assertEquals("odbywał się",determineTime);
    }

    @Test
    public void determineTimeWhenEventIsNow() {
        //given
        LocalDate from = LocalDate.now().minusDays(1);
        LocalDate to = LocalDate.now().plusDays(10);
        //when
        String determineTime = timeUtil.determineTime(from, to);
        //then
        assertEquals("trwa",determineTime);
    }

    @Test
    public void determineTimeWhenEventWillBe() {
        //given
        LocalDate from = LocalDate.now().plusDays(10);
        LocalDate to = LocalDate.now().plusDays(20);
        //when
        String determineTime = timeUtil.determineTime(from, to);
        //then
        assertEquals("odbędzie sie",determineTime);
    }

    @Test
    public void showDifference() {
        //given
        LocalDate from = LocalDate.now();
        LocalDate to = LocalDate.now().plusDays(15);
        //when
        Map<String, Long> stringLongMap = timeUtil.showTimeDifference(from, to);
        String key = null;
        Long value = null;
        for (Map.Entry<String, Long> stringLongEntry : stringLongMap.entrySet()) {
            key = stringLongEntry.getKey();
            value = stringLongEntry.getValue();
        }
        String result = "" + value + key;
        //then
        assertFalse(stringLongMap.isEmpty());
        assertEquals("dni", key);
        assertEquals(15, (long) value);
        Assert.assertEquals("15dni", result);
    }

    @Test
    public void printTimePeriod() {
        //given
        LocalDate from = LocalDate.now().minusDays(1);
        LocalDate to = LocalDate.now().plusDays(10);
        //when
        String timePeriod = timeUtil.printTimePeriod(from, to);
        //then
        assertEquals("Event odbywa się", timePeriod);
    }

    @Test
    public void printTimeToEnd() {
        //given
        LocalDate from = LocalDate.now().minusDays(1);
        LocalDate to = LocalDate.now().plusDays(10);
        //when
        timeUtil.showTimeDifference(from, to);
        String timeToEnd = timeUtil.printTimeToEnd();
        //then
        assertEquals("Do zakończenia eventu pozostało 11 dni,",timeToEnd);
    }
}