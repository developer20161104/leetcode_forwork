package basement;
import java.util.*;

public class LinkListTest {
    public static void main(String[] args) {
        List<String> a = new LinkedList<>();

        a.add("any");
        a.add("calr");
        a.add("Erica");

        List<String> b = new LinkedList<>();

        b.add("bob");
        b.add("france");
        b.add("africa");

        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();

        // combine two list
        while(bIter.hasNext()){
            if(aIter.hasNext()) aIter.next();
            aIter.add(bIter.next());
        }

        System.out.println(a);

        a.removeAll(b);
        System.out.println(a);

        a.addAll(b);
        System.out.println(a);
    }

}
