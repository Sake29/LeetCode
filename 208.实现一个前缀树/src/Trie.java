import org.junit.Test;

import java.util.HashMap;

public class Trie {

    private TrieNode root;//字典树的根节点
    public Trie(){
        root = new TrieNode();
    }

    public void insert(String word){
        if ((word == null)||word.equals("")) return;
        TrieNode node = root;
        for (int i = 0; i < word.length() ; i++) {
            char ch = word.charAt(i);
            //如果根节点的下一个节点不包含这个字母
            if (!node.next.containsKey(ch)){
                //生成一个字母节点
                node.next.put(ch,new TrieNode());
            }
            node = node.next.get(ch);
        }
        //插入完毕后，对最后一个节点的end进行计数
        //当end不为0时，说明该节点是单词的末尾了。
        node.end++;
    }

    public boolean search(String word){
        if (word == null||word.equals("")) return false;
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.next.containsKey(ch)) return false;
            node = node.next.get(ch);
        }
        //当单词的最后一个字符所对应的节点的end不为0时，
        //说明该单词一定是另一个单词的前缀
        //此时返回false
        if (node.end == 0) return false;
        return true;
    }

    public boolean startWith(String word){
        if (word==null||word.equals("")) return false;
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.next.containsKey(ch)) return false;
            node = node.next.get(ch);
        }
        return true;
    }

    @Test
    public void test(){
        Trie trie = new Trie();
        System.out.println("---insert测试---");
        trie.insert("fuck");
        trie.insert("funk");
        trie.insert("duck");
        System.out.println("insert完毕！");

        System.out.println("---search测试---");
        System.out.println("测试结果："+trie.search("fuck")+"，预期结果：true");
        System.out.println("测试结果："+trie.search("fuuk")+"，预期结果：false");
        System.out.println("测试结果："+trie.search("funk")+"，预期结果：true");
        System.out.println("测试结果："+trie.search("")+"，预期结果：false");

        System.out.println("---前缀查询测试---");
        System.out.println("测试结果："+trie.startWith("fu")+"，预期结果：true");
        System.out.println("测试结果："+trie.startWith("f")+"，预期结果：true");
        System.out.println("测试结果："+trie.startWith("d")+"，预期结果：true");
    }

}

class TrieNode{
    public int end;//表示以当前节点为结尾的单词的个数
    public HashMap<Character,TrieNode> next;//表示当前节点能链接到的所有节点
    public TrieNode(){
        end = 0;
        next = new HashMap<>();
    }
}
