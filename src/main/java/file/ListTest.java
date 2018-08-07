package file;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * list对象内部数据修改
 *
 * @author yafei.hou  on 2018/7/30
 */
public class ListTest {

    public static void main(String[] args){
        List<Book> books = Lists.newArrayList();
        books.add(new Book("java高级编程",902));
        books.add(new Book("c++高级编程",871));
        books.add(new Book("python高级编程",162));
        books.add(new Book("scala高级编程",320));
        System.out.println(books);
        changeList(books);
        System.out.println(books);
    }

    public static void changeList(List<Book> books){
        books.forEach((item)->{
            if (item.getTitle().contains("java")){
                item.setTitle("java 入门到精通");
            }
        });
    }

}

class Book{

    private String title;

    private int pages;

    public Book() {
    }

    public Book(String title, int pages) {
        this.title = title;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", pages=" + pages +
                '}';
    }
}