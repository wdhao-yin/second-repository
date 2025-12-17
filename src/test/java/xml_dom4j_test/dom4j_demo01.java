package xml_dom4j_test;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class dom4j_demo01 {
    public static void main(String[] args) throws DocumentException {

        ArrayList<Student> students = new ArrayList<>();

        //  获取SAXReader对象
        SAXReader reader = new SAXReader();
        //  定义xml的file路径，接着传入read方法获取Document对象
        File file=new File("D:\\java\\java-code\\maven-test\\Springboot-web-quickstart\\Springboot-web-quickstart\\src\\main\\test_xml.xml");
        Document dc = reader.read(file);
        //然后根据文件对象获取根元素（标签）
        Element rootElement = dc.getRootElement();
        //再用根标签获取其他节点（node）
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            //再把获取的下层节点分别遍历，分别获取其文本内容和属性
            //获取属性
            Attribute id = element.attribute("id");
            String idValue = id.getText();

            //获取更下层元素以及其文本内容
            Element name = element.element("name");
            String nameValue = name.getText();

            Element gender = element.element("gender");
            String genderValue = gender.getText();

            Element phoneid = element.element("phoneid");
            String phoneValue = phoneid.getText();

            Student stu=new Student(nameValue,genderValue,phoneValue);
            students.add(stu);
            System.out.println(idValue);
        }

        students.forEach(System.out::println);

        System.out.println("--------------------------");
        //通过Xpath解析检索
        //  获取SAXReader对象
        SAXReader reader2 = new SAXReader();
        //  定义xml的file路径，接着传入read方法获取Document对象
        Document dc2 = reader2.read(new File("D:\\java\\java-code\\maven-test\\Springboot-web-quickstart\\Springboot-web-quickstart\\src\\main\\test_xml.xml"));
        //批量检索,使用绝对路径的方式
        List<Node> nodes = dc2.selectNodes("/Students/Student/name");
        //再通过节点获取内容
        for (Node node : nodes) {
            System.out.println(node.getText());
        }

        System.out.println("-----------------------");
        //使用相对路径
        Element rootElement1 = dc2.getRootElement();
        List<Node> nodes1 = rootElement1.selectNodes("./Student/name");
        for (Node node : nodes1) {
            System.out.println(node.getText());
        }

        System.out.println("----------------------");
        //全文检索，使用/是单级检索，使用//是多级检索
        List<Node> nodes2 = dc2.selectNodes("//name");
        for(Node node : nodes2) {
            System.out.println(node.getText());
        }
        //除了标签查找外，还能进行属性查找  //@属性  查属性  //标签[@属性] 查对应属性的标签 //标签[@属性=‘值’] 查对应属性值的标签

    }
}
