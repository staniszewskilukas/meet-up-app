//package pl.sda.meetup.myownmeetup.date_and_time;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.time.LocalDateTime;
//import java.time.Period;
//import java.util.Map;
//
//import static org.junit.Assert.*;
//
//public class TimeUtilTest {
//
//
//    @Test
//    public void showDifference() {
//        //given
//        LocalDateTime from = LocalDateTime.now();
//        LocalDateTime to = LocalDateTime.now().plusDays(15);
//        TimeUtil timeUtil = new TimeUtil(from,to);
//        //when
//        Map<String, Long> stringLongMap = timeUtil.showDifference();
//        String key = null;
//        Long value = null;
//        for (Map.Entry<String, Long> stringLongEntry : stringLongMap.entrySet()) {
//            key = stringLongEntry.getKey();
//            value = stringLongEntry.getValue();
//        }
//        String result = "" + value + key;
//        //then
//        assertFalse(stringLongMap.isEmpty());
//        assertEquals("dni", key);
//        assertEquals(15, (long) value);
//        Assert.assertEquals("15dni",result);
//    }
//
//    @Test
//    public void printTimePeriod() {
//    }
//
//    @Test
//    public void printTimeToEnd() {
//    }
//
//    //musi też tu byc metoda która będzie zmieniać format czasu z 2020-06-08 na 08-06-2020
//    //lub z 10:48 06-08-2020 na coś innego itd.
//}