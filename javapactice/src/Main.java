import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("Banana","Apple","mango","apple");
       List<String> resut= fruits.stream().filter(i -> i.startsWith("A")).collect(Collectors.toList());

        System.out.println(resut);
    }
    }
