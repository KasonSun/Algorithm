package interview.hw.shixi;

import java.util.*;

/**
 * 舆情热词
 * 输入两个整数：topN-代表排序输出后的前N个词，M代表文章数，每篇文章包含标题和正文
 * 其中标题的词频权重为3， 正文中词频系数为1
 * 返回的答案按照词语出现的频率由高到低排序，当词语频率相同时，在标题中出现频率次数高的排在前面；
 * 如果仍然相同，则按照词语在标题中的先后顺序进行排序，如果仍然相同，则按照词语在正文中出现的先后顺序进行排序。
 *
 * 思路：使用四个Map分别统计标题的词频以及每个词第一次出现的顺序。在统计过程中使用set统计每个词。
 * 然后遍历set，使用自定义封装类Container将当前词、总的词频、标题词频、标题词序、正文词频、正文词序放入Container，
 * 让后将Container放入List<Container>提供按照上述规则的比较器进行排序，最后进行输出。
 *
 * 3 2
 * xinguan feiyan xinzeng bendi quezhen anli
 * ju baodao chengdu xinzeng xinguan feiyan bendi quezhen anli yili shenzhen xinzeng bendi quezhen anli liangli yiqing zhhengti kongzhi lianghao
 * xinguan yimiao linchuang shiyan
 * wuzhong xinguan yimiao tongguo sanqi linchuang shiyan xiaoguo lianghao
 */
public class BreakWordTopN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int topN = 0;
        int articles = 0;
        String params = sc.nextLine();
        String[] param = params.split(" ");
        topN = Integer.valueOf(param[0]);
        articles = Integer.valueOf(param[1]);
        //System.out.println("topN:" + topN + "，articles:" + articles);

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
                titles.put(word, titles.getOrDefault(word, 0) + 1);
                if (!titlesSeq.containsKey(word)) {
                    titlesSeq.put(word, seqTitle++);
                }
                words.add(word);
            }

            //统计正文词频和词序
            String lineContent = sc.nextLine();
            for (String word : lineContent.split(" ")) {
                contents.put(word, contents.getOrDefault(word, 0) + 1);
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
            if (titles.containsKey(word) && contents.containsKey(word)) {//1.标题、正文都出现
                container = new Container(word, titles.get(word) * 3 + contents.get(word), titles.get(word), titlesSeq.get(word),
                        contents.get(word), contentsSeq.get(word));
            } else if (!titles.containsKey(word) && contents.containsKey(word)) {//2.标题没有，正文出现
                container = new Container(word, contents.get(word), 0, contentsSeq.get(word),
                        contents.get(word), contentsSeq.get(word));
            } else {//3.标题出现，正文没有
                container = new Container(word, titles.get(word) * 3, titles.get(word), titlesSeq.get(word),
                        0, titlesSeq.get(word));
            }
            containerList.add(container);
            containerList.sort(new Comparator<Container>() {
                @Override
                public int compare(Container o1, Container o2) {
                    if (o1.getWordFrquences() != o2.getWordFrquences())
                        return o2.getWordFrquences() - o1.getWordFrquences();
                    else if (o1.getTitles() != o2.getTitles())
                        return o2.getTitles() - o1.getTitles();
                    else if (o1.getTitlesSeq() != o2.getTitlesSeq())
                        return o1.getTitlesSeq() - o2.getTitlesSeq();
                    else if (o1.getContents() != o2.getContents())
                        return o2.getContents() - o1.getContents();
                    else if (o1.getContentsSeq() != o2.getContentsSeq())
                        return o1.getContentsSeq() - o2.getContentsSeq();
                    else return 0;//0的含义是在两个元素相同时，不交换顺序（为了排序的稳定性，可以使用1来代替0，不要用-1来代替0）
                }
            });
        }
        //调试信息
        /*for (int i = 0; i < containerList.size(); i++) {
            System.out.println(containerList.get(i));
        }*/
        //输出最终结果
        for (int i = 0; i < topN - 1; i++) {
            System.out.print(containerList.get(i).getWord() + " ");
        }
        System.out.print(containerList.get(topN - 1).getWord());
    }
}

class Container {
    private String word;//单个词
    private int wordFrquences;//词频
    private int titles;//标题词频
    private int titlesSeq;//标题词序
    private int contents;//正文词频
    private int contentsSeq;//正文词序

    public Container() {
    }

    public Container(String word, int wordFrquences, int titles, int titlesNum, int contents, int contentsNum) {
        this.word = word;
        this.wordFrquences = wordFrquences;
        this.titles = titles;
        this.titlesSeq = titlesNum;
        this.contents = contents;
        this.contentsSeq = contentsNum;
    }

    public int getWordFrquences() {
        return wordFrquences;
    }

    public int getTitles() {
        return titles;
    }

    public int getTitlesSeq() {
        return titlesSeq;
    }

    public int getContents() {
        return contents;
    }

    public int getContentsSeq() {
        return contentsSeq;
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
                ", titlesNum=" + titlesSeq +
                ", contents=" + contents +
                ", contentsNum=" + contentsSeq +
                '}';
    }
}
