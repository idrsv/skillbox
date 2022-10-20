import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader
{
    public static void main(String[] args) {
        final String regex = "([0-9]+)";
        final String text = "Вася заработал 99999000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей , Катя - 200000 рублей";
        int sum = 0;
        
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            sum += Integer.parseInt(matcher.group()) ;
        }
        System.out.println(sum);
    }
}