package interview.hw;

import java.util.*;

public class BreakWordTopN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int topN=0;
        int articles=0;
        String params = sc.nextLine();
        String[] param = params.split(" ");
        topN = Integer.valueOf(param[0]);
        articles = Integer.valueOf(param[1]);
        System.out.println("topN:" + topN+"，articles:" + articles);

        Map<String, Integer> titles = new HashMap<>();//标题中词频
        Map<String, Integer> titlesSeq = new HashMap<>();//标题中第一次出现这个词的顺序
        Map<String, Integer> contents = new HashMap<>();//正文中词频
        Map<String, Integer> contentsSeq = new HashMap<>();//正文中第一次出现这个词的顺序
        Set<String> words = new HashSet();

        int seqTitle = 0;//标题词顺序索引
        int seqContent = 0;//正文词顺序索引
        for (int i = 0; i < articles; i++) {
            String lineTitle = sc.nextLine();
            //统计标题词频和词序
            for (String word : lineTitle.split(" ")) {
                if (titles.containsKey(word)) {
                    titles.put(word, titles.getOrDefault(word, 0) + 1);
                }else{
                    titles.put(word, 1);
                }
                if (!titlesSeq.containsKey(word)) {
                    titlesSeq.put(word, seqTitle++);
                }
                words.add(word);
            }

            //统计正文词频和词序
            String lineContent = sc.nextLine();
            for (String word : lineContent.split(" ")) {
                if (contents.containsKey(word)) {
                    contents.put(word, titles.getOrDefault(word, 0) + 1);
                }else{
                    contents.put(word, 1);
                }
                if (!contentsSeq.containsKey(word)) {
                    contentsSeq.put(word, seqContent++);
                }
                words.add(word);
            }
        }
        //遍历词集合，将每个词进行词频、词序统计，包装放入list
        List<Container> containerList = new ArrayList<>();
        for (String word : words) {
            Container container = null;
            //word出现分三种情况
            if(titles.containsKey(word) && contents.containsKey(word)){//1.标题、正文都出现
                container =new Container(word, titles.get(word) * 3 + contents.get(word), titles.get(word), titlesSeq.get(word),
                        contents.get(word), contentsSeq.get(word));
            } else if (!titles.containsKey(word) && contents.containsKey(word)) {//1.标题没有，正文出现
                container=new Container(word, contents.get(word), 0, contentsSeq.get(word),
                        contents.get(word), contentsSeq.get(word));
            }else{//3.标题出现，正文没有
                container=new Container(word, titles.get(word) * 3, titles.get(word), titlesSeq.get(word),
                        0, titlesSeq.get(word));
            }
            containerList.add(container);
            containerList.sort(new Comparator<Container>() {
                @Override
                public int compare(Container o1, Container o2) {
                    if(o1.getWordFrquences()!=o2.getWordFrquences()) return o2.getWordFrquences()-o1.getWordFrquences();
                    else if(o1.getTitles()!=o2.getTitles()) return o2.getTitles() - o1.getTitles();
                    else if(o1.getTitlesNum()!=o2.getTitlesNum()) return o1.getTitlesNum() - o2.getTitlesNum();
                    else if(o1.getContents()!=o2.getContents()) return o2.getContents() - o1.getContents();
                    else if(o1.getContentsNum()!=o2.getContentsNum()) return o1.getContentsNum() - o2.getContentsNum();
                    else return 0;
                }
            });
        }
        //调试信息
        for (int i = 0; i < containerList.size(); i++) {
            System.out.println(containerList.get(i));
        }
        //输出最终结果
        for (int i = 0; i < topN-1; i++) {
            System.out.print(containerList.get(i).getWord()+" ");
        }
        System.out.print(containerList.get(topN-1).getWord());
    }
}

class Container{
    private String word;//单个词
    private int wordFrquences;//词频
    private int titles;//标题词频
    private int titlesNum;//标题词序
    private int contents;//正文词频
    private int contentsNum;//正文词序

    public Container(){
    }

    public Container(String word, int wordFrquences, int titles, int titlesNum, int contents, int contentsNum) {
        this.word = word;
        this.wordFrquences = wordFrquences;
        this.titles = titles;
        this.titlesNum = titlesNum;
        this.contents = contents;
        this.contentsNum = contentsNum;
    }

    public int getWordFrquences() {
        return wordFrquences;
    }

    public int getTitles() {
        return titles;
    }

    public int getTitlesNum() {
        return titlesNum;
    }

    public int getContents() {
        return contents;
    }

    public int getContentsNum() {
        return contentsNum;
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return "Container{" +
                "word='" + word + '\'' +
                ", wordFrquences=" + wordFrquences +
                ", titles=" + titles +
                ", titlesNum=" + titlesNum +
                ", contents=" + contents +
                ", contentsNum=" + contentsNum +
                '}';
    }
}
