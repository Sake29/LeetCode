import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class MapSum {
    class TrieNode{
        public int value;//当前节点的值
        public HashMap<Character,TrieNode> next;//表示当前节点能链接到的所有节点
        public TrieNode(){
            value = 0;
            next = new HashMap<>();
        }
    }
    private TrieNode root;
    private HashMap<String,Integer> keys = new HashMap<String, Integer>();

    public MapSum(){
        root = new TrieNode();
    }

    public void insert(String key,int val ){
        if (keys.containsKey(key)){
            TrieNode node = root;
            for (int i = 0; i < key.length();i++) {
                char ch = key.charAt(i);
                if (!node.next.containsKey(ch)){
                    TrieNode newNode = new TrieNode();
                    newNode.value = val;
                    node.next.put(ch,newNode);
                }
                else {
                    node.next.get(ch).value = val;
                }
                node = node.next.get(ch);
            }
            keys.put(key,val);
            return;
        }
        else {
            TrieNode node = root;
            for (int i = 0; i < key.length();i++) {
                char ch = key.charAt(i);
                if (!node.next.containsKey(ch)){
                    TrieNode newNode = new TrieNode();
                    newNode.value = val;
                    node.next.put(ch,newNode);
                }
                else {
                    node.next.get(ch).value += val;
                }
                node = node.next.get(ch);
            }
            keys.put(key,val);
        }
    }

    public int sum(String prefix){
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (!node.next.containsKey(ch)) return 0;
            node = node.next.get(ch);
        }
        return node.value;
    }


/*    private TrieNode root;
    public MapSum(){
        root = new TrieNode();
    }

    public void insert(String key,int val){
        TrieNode node = root;
        for (int i = 0; i < key.length() ; i++) {
            char ch = key.charAt(i);
            if (!node.next.containsKey(ch)){
                node.next.put(ch,new TrieNode());
            }
            node = node.next.get(ch);
        }
        //在最后一个节点中，存储val
        node.value = val;
    }

    public int sum(String prefix){
        TrieNode node = root;
        for (int i = 0; i < prefix.length() ; i++) {
            char ch = prefix.charAt(i);
            if (!node.next.containsKey(ch)){
                return 0;
            }
            node = node.next.get(ch);
        }
        return sumNode(node);
    }

    private int sumNode(TrieNode node){
        int count = node.value;
        for (Character ch : node.next.keySet()) {
            count += sumNode(node.next.get(ch));
        }
        return count;
    }*/

    @Test
    public void test(){
        this.insert("apple",3);
        int sum = this.sum("ap");
        System.out.println(sum);
        this.insert("app",2);
        int sum1 = this.sum("ap");
        System.out.println(sum1);
    }
}


