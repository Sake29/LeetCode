import org.junit.Test;

public class Main {

    public static void main(String[] args) {
        LinkList linkList = new LinkList();
        for (int i = 0; i < 5; i++) {
            linkList.add(i);
        }
        while (linkList.head!=null){
            linkList.print(linkList.head);
            linkList.head = linkList.head.next;
        }


    }
    @Test
    public void test()
    {
        int i = 3;
        int j =4;
        i=j;
        System.out.println("i:"+i);
        System.out.println("j:"+j);
    }

}
