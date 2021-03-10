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
        assertEquals("zakończył się",determineTime);
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

    //---------------------------------------powyżej ok---------------------------------------------------------------//

    @Test
    public void countPeriod_EventLast3Months_WillCountAmountOfMonths(){

    }

    @Test
    public void countPeriod_EventLast12Days_WillCountAmountOfDays(){

    }

    @Test
    public void countPeriod_EventLast6MonthsAnd12Days_WillCountAmountOfMonthsDays(){

    }

    @Test
    public void countPeriod_EventLast2Years6Months12Days_WillCountAmountOfYearsMonthsDays(){

    }



    @Test
    public void showDifference() { //TODO ta metoda źle działa więc albo test jest do dupy albo więcej testów
        //TODO tu dodać funkcjonalność że jeśli event jeszcze się nie zaczął to liczy czas do początku a jeśli ju trwa to liczy czas do zakończenia, jeśli się zakończył to liczy czas do zakończenia wstecz
        //TODO dla zakończonych eventów zrobić osobną metodę liczącą
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
    public void methodName_EventEnded2MonthsEgo_WillCountAndPrintTimeFormNowToEventEnd(){
        //given
        LocalDate start = LocalDate.now().minusMonths(6);
        LocalDate end = LocalDate.now().minusMonths(2);
        //when
        Map<String, Long> stringLongMap = timeUtil.showTimeDifference(start, end);
        String expected =timeUtil.printTime();
        //then
        assertEquals("Event zakończył się 2 miesiące temu", stringLongMap);

    }

    //TODO zrobić metodę wyliczającą czas trwania eventu

    @Test
    public void printTimePeriod() {
        //given
        LocalDate from = LocalDate.now().minusDays(1);
        LocalDate to = LocalDate.now().plusDays(10);
        //when
        String timePeriod = timeUtil.printTimePeriod(from, to);
        //then
        assertEquals("Event trwa", timePeriod);
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
        assertEquals("Do zakończenia eventu pozostało: 11 dni",timeToEnd);
    }

    //TODO zrobić test metody namesTimeDifferences
}