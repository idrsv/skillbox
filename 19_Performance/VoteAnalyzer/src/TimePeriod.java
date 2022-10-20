import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimePeriod implements Comparable<TimePeriod>
{
    private long from;
    private long to;
    private static long day = 86400000;
    private static SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    /**
     * Time period within one day
     * @param from
     * @param to
     */

//    public TimePeriod(long from, long to)
//    {
//        this.from = from;
//        this.to = to;
//        if(!dayFormat.format(new Date(from)).equals(dayFormat.format(new Date(to))))
//            throw new IllegalArgumentException("Dates 'from' and 'to' must be within ONE day!");
//    }

    public TimePeriod(long from, long to) {
        this.from = from;
        this.to = to;
        if (from / day != to / day)
            throw new IllegalArgumentException("Dates 'from' and 'to' must be within ONE day !");
    }

    public void appendTime(long visitTime)
    {
        if(this.from / day != visitTime / day)
            throw new IllegalArgumentException("Visit time must be within the same day as the current TimePeriod!");
        if(visitTime < from) {
            from = visitTime;
        }
        if(visitTime > to) {
            to = visitTime;
        }
    }

    public String toString()
    {
        String from = dayFormat.format(this.from);
        String to = timeFormat.format(this.to);
        return from + "-" + to;
    }

    @Override
    public int compareTo(TimePeriod period)
    {
        return (int )(this.from / day - period.from / day);
    }
}
