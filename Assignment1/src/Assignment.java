import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Assignment {

 public static <T> List<T> longestSmallerPrefix(List<T> a, List<T> b, Comparator<? super T> cmp) {
  
  List<T> list = a.stream()
                     .filter(i -> cmp.compare(i, b.get(a.indexOf(i))) <= 0)
                     .collect(Collectors.toList());
  
  return list;

  
 }
 
 public static void main(String[] args) {
   List<Integer> list = new ArrayList<Integer>();
   list.add(1);
   list.add(2);
   list.add(3);
   list.add(4);
   list.add(5);
   List<Integer> list2 = new ArrayList<Integer>();
   list2.add(2);
   list2.add(3);
   list2.add(4);
   list2.add(4);
   list2.add(5);
   List<Integer> list3 = Assignment.longestSmallerPrefix(list, list2, new Comparator<Integer>() {
    @Override
    public int compare(Integer s1, Integer s2) {
        return s1.compareTo(s2);
    }
});
   System.out.println(list3);
 }
}
