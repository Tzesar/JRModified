package net.sf.jasperreports.engine.fill;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.JRTextField;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by augusto on 3/4/15.
 */
public class ElumenRichTextHelper {

    // my changes
    public static List<JRFillElement> processRichText(JRElement element, JRFillElement[] elements, JRBaseFiller filler) throws JRException {

        List<JRFillElement> elementList = new ArrayList<JRFillElement>();

        if(element instanceof JRTextField){

            JRFillTextField textField = (JRFillTextField)element;
            if ( textField.getText() != null ){
                String elementContent = textField.getText().replaceAll("&nbsp", "");
                System.out.println("JRTextField: " + elementContent);

                if (elementContent != null && !Jsoup.isValid(elementContent, Whitelist.none())) {
                    System.out.println("RICH TEXT");

                    Element elementDOM = Jsoup.parse(elementContent).body();
                    Elements parents = elementDOM.children();

                    for( Element parent : parents){
                        Elements imageList = new Elements();
                        if ( parent.getElementsByTag("img").size() > 0 && !"img".equals(parent.tagName()) ){
                            imageList = parent.getElementsByTag("img");
                            parent.getElementsByTag("img").remove();
                        }

                        if ( "img".equals(parent.tagName()) ){
                            try{
                                JRImage image = (JRImage)element;
                                JRFillImage fillImage = new JRFillImage(filler,  image, new JRFillObjectFactory(filler));
                            } catch (ClassCastException ex){
                                System.out.println("Image Error:" + ex.getMessage() );
                            }
//                            String styleAttr = parent.attr("style");
//                            URL url = null;
//
//                            try{
//                                url = new URL(parent.attr("src"));
//                            } catch (MalformedURLException ex){
//                                System.out.println(ex);
//                            }
//
//                            expression = new JRDesignExpression();
//                            expression.setText(parent.attr("src"));
//                            JRImageRenderer imageRenderer = JRImageRenderer.getInstance(
//                                    JRLoader.loadBytes(url)
//                            );
//
//                            String imageHeight = Integer.toString((int)imageRenderer.getDimension().getHeight());
//                            String imageWidth = Integer.toString((int)imageRenderer.getDimension().getWidth());
//                            if ( styleAttr.contains("height") ){
//                                imageHeight = styleAttr.substring(styleAttr.indexOf("height:"), styleAttr.indexOf("px;", styleAttr.indexOf("height:")));
//                            }
//                            if ( styleAttr.contains("width") ){
//                                imageWidth = styleAttr.substring(styleAttr.indexOf("width:"), styleAttr.indexOf("px;", styleAttr.indexOf("width:")));
//                            }
//
//                            Integer iWidth = Integer.parseInt(imageWidth);
//                            Integer iHeight = Integer.parseInt(imageHeight);
//
//                            Object object = new Object();
//                            JRDesignImage imageElement = (JRDesignImage) object;
//
//                            imageElement.setWidth(iWidth);
//                            imageElement.setHeight(iHeight);
//                            imageElement.setScaleImage(ScaleImageEnum.CLIP);
//                            imageElement.setExpression(expression);
//
//                            elementList.add(imageElement);
                        } else {
                            System.out.println("Creating Styled Text");
                            for (int i=0;i<5;i++){
                                JRFillTextFieldCustomized textElement = new JRFillTextFieldCustomized(textField, new JRFillCloneFactory());
                                textElement.setRawText("PIZZAAAAAAA " + parent.html() + i);
                                textElement.getStyledText();
                                textElement.setY(textElement.getY() + i*textElement.getHeight());
                                elementList.add(textElement);
                            }
                        }
                        for ( Element image : imageList ){
                            try{
                                JRImage jrImage = (JRImage)element;
                                JRFillImage fillImage = new JRFillImage(filler, jrImage, new JRFillObjectFactory(filler));
                            } catch (ClassCastException ex){
                                System.out.println("Image Error:" + ex.getMessage() );
                            }
//                        String styleAttr = image.attr("style");
//                        URL url = null;
//
//                        try{
//                            url = new URL(image.attr("src"));
//                        } catch (MalformedURLException ex){
//                            System.out.println(ex);
//                        }
//
//                        expression = new JRDesignExpression();
//                        expression.setText(image.attr("src"));
//                        JRImageRenderer imageRenderer = JRImageRenderer.getInstance(
//                                JRLoader.loadBytes(url)
//                        );
//
//                        String imageHeight = Integer.toString((int)imageRenderer.getDimension().getHeight());
//                        String imageWidth = Integer.toString((int)imageRenderer.getDimension().getWidth());
//                        if ( styleAttr.contains("height") ){
//                            imageHeight = styleAttr.substring(styleAttr.indexOf("height:"), styleAttr.indexOf("px;", styleAttr.indexOf("height:")));
//                        }
//                        if ( styleAttr.contains("width") ){
//                            imageWidth = styleAttr.substring(styleAttr.indexOf("width:"), styleAttr.indexOf("px;", styleAttr.indexOf("width:")));
//                        }
//
//                        Integer iWidth = Integer.parseInt(imageWidth);
//                        Integer iHeight = Integer.parseInt(imageHeight);
//
//                        Object object = new Object();
//                        JRDesignImage imageElement = (JRDesignImage) object;
//
//                        imageElement.setWidth(iWidth);
//                        imageElement.setHeight(iHeight);
//                        imageElement.setScaleImage(ScaleImageEnum.CLIP);
//                        imageElement.setExpression(expression);
//
//                        elementList.add(imageElement);
                        }
                    }
                    elementList.addAll(Arrays.asList(elements));
//        List<JRFillElement> elementsList = Arrays.asList(this.elements);
                    if (elementList.indexOf(textField)>0)
                        System.out.println("Removed original element");
                    elementList.remove(textField);
                    elements = new JRFillElement[elementList.size()];
                    elementList.toArray(elements);
                    for (JRFillElement fillElement : elements){
                        try {
                            System.out.println("AFTER PrintText: " + ((JRFillTextField) fillElement).getText() + " UUID:"+fillElement.getUUID());
                        } catch (ClassCastException ex){}
                    }
                }else{
                    elementList.add((JRFillElement)element);
                }
            }
        }else{
            elementList.add((JRFillElement)element);
        }

        return elementList;
    }
}
