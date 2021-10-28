import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
public class YX {
    public static void main(String[] args){
        try {
            ArrayList<Object> X = new ArrayList<>();/////лист для координат X
            ArrayList<Object> Y = new ArrayList<>();/////лист для координат Y
            ArrayList<Object> R = new ArrayList<>();/////лист для номера рисунка
            String[] number_img = {"square1.png", "square2.png", "square3.png", "square4.png", "square5.png","Circle1.png", "Circle2.png", "Circle3.png", "Circle4.png", "Circle5.png","Triangle1.png","Triangle2.png","Triangle3.png","Triangle4.png","Triangle5.png"};////массив названий рисунков
            for (int i = 0; i < number_img.length; i++) {///цикл для поочередной адресации к рисункам по названию
            String adres = "C:\\Users\\Admin\\ideaProjects\\Pattern Determinant\\shapes\\square\\"+number_img[i];////адрес конкретного рисунка
            File file = new File(adres);///запись адреса в файл
            BufferedImage img = ImageIO.read(file);///чтение файла и заполнение буфера пикселями
            for (int x=0; x < img.getWidth(); x++){///цикл чтения пикселей по Y
                for (int y=0; y < img.getWidth(); y++){///цикл чтения пикселей по Х
                    Color color = new Color(img.getRGB(x, y));///переменная для определения цвета пикселя
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    if (red<255&green<255&blue<255) {///условие для заполнения списка не белыми пиксилями
                        X.add(x);
                        Y.add(y);
                        R.add(i+1);
                    }
                    }
                }
            }
            int size = X.size();///размер списков с небелыми пикселями
            int [][] arr = new int[size][3];///массив для координат(ХУ) и номера рисунков
            for (int i = 0; i < arr.length; i++){///цикл заполнения массива
                arr[i][0]=(int) X.get(i);
                arr[i][1]=(int) Y.get(i);
                arr[i][2]=(int) R.get(i);
            }
            while (size>0){///очистка списков от значений
                X.remove(size-1);
                Y.remove(size-1);
                size--;
            }
            //System.out.println(Arrays.deepToString(arr));///вывод массива координат(ХУ) и номера рисунков
            String exadres = "C:\\Users\\Admin\\ideaProjects\\Pattern Determinant\\shapes\\square\\experimentpng.png";////адрес исследуемого рисунка
            File file = new File(exadres);///запись адреса в файл
            BufferedImage img = ImageIO.read(file);///чтение файла и заполнение буфера пикселями
            for (int x=0; x < img.getWidth(); x++){///цикл чтения пикселей по Y
                for (int y=0; y < img.getWidth(); y++){///цикл чтения пикселей по Х
                    Color color = new Color(img.getRGB(x, y));///переменная для определения цвета пикселя
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    if (red<255&green<255&blue<255) {///условие для заполнения списка не белыми пиксилями
                        X.add(x);
                        Y.add(y);
                    }
                }
            }
            int size_ex = X.size();///размер списка с небелыми пикселями
            int [][] arr_ex = new int[size_ex][2];///массив для координат исследуемого рисунка
            for (int i = 0; i < arr_ex.length; i++){///цикл заполнения массива
                arr_ex[i][0]=(int) X.get(i);
                arr_ex[i][1]=(int) Y.get(i);
            }
            //System.out.println(Arrays.deepToString(arr_ex));///вывод массива координат не белых пикселей исследуемого рисунка
            int [][] arr_result = new  int[arr_ex.length][number_img.length];///массив длинной равной массиву координат не белых пикселей исследуемого рисунка и шириной равной колличеству исходных рисунков
            for (int i = 0; i < arr_ex.length; i++){
                for (int[] ints : arr) {///цикл заполнения массива еденицами в том случае если координаты не белых пикселей исследуемого рисунка совпадают с координатами не белых пикселей исходных рисунков
                    if (arr_ex[i][0] == ints[0] & arr_ex[i][1] == ints[1]) {
                        arr_result[i][ints[2] - 1] = 1;
                    }
                }
            }
            //System.out.println(Arrays.deepToString(arr_result));///вывод массива
            double pix_proc =100/(double)size_ex;///процент занимаемый координатой с не белым пикселем в массиве исследуемого рисунка
            //double sum_pix;
            double[][]arr_proc = new double[arr_result.length][number_img.length];//массив в который зиписываются совпадения исследуемого рисунка с исходными в процентах для каждого пикселя
            for (int i = 0; i < arr_result.length; i++){
                for (int q = 0; q < number_img.length; q++){
                    if (arr_result[i][q]==1){
                        //sum_pix = IntStream.of(arr_result[i]).sum();
                        //double e=pix_proc/sum_pix;
                        ///System.out.println(pix_proc+" "+"/"+" "+sum_pix+" "+"="+" "+e);
                        for (int w = 0; w < number_img.length; w++){
                            if (arr_result[i][w]==1){arr_proc[i][w]=pix_proc;}
                        }
                    break;}
                }
            }
            //System.out.println(Arrays.deepToString(arr_proc));
            double[] itog = new double[number_img.length];//массив результатов
            for (double[] doubles : arr_proc) {
                for (int q = 0; q < number_img.length; q++) {
                    itog[q] = itog[q] + doubles[q];
                }
            }
            //System.out.println(Arrays.deepToString(new double[][]{itog}));
            double max=0;//переменная максимального значения массива результатов
            int key=0;//переменная индекса максимального значения массива результатов
            for (int i=0; i<itog.length; i++){//в цикле ноходим максимальное значение массива результатов и его индекс
                if (itog[i]>max){
                    max=itog[i];
                    key=i;
                }
            }
            System.out.println("Исследуемый рисунок: "+exadres+" похож на рисунок:"+number_img[key]+" с вероятностью: "+max+"   %.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
