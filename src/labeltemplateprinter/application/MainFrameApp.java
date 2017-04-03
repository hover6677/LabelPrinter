/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labeltemplateprinter.application;

import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static labeltemplateprinter.application.MailingTemplate.formatRecords;
import org.fileRW.FileRW;
import org.json.JSONObject;

/**
 *
 * @author admin1
 */
public class MainFrameApp {

    private static int tabCount = 0;
    private static ArrayList recordList;
    private static ArrayList<ArrayList> templateList;
    private static final String desktopPath = System.getProperty("user.home") + "\\" + "Desktop";
//    private static final ArrayList<String> dirList =new ArrayList<String> 
//                    (Arrays.asList("D:/NetBeansProj/LabelTemplatePrinter/output/templateA/A.txt",
//                            "D:/NetBeansProj/LabelTemplatePrinter/output/templateB/B.txt"));
    private static final ArrayList<String> dirList = new ArrayList<String>(Arrays.asList(desktopPath + "\\Mailing.txt",
            desktopPath + "\\Identity.txt"));
    private static String dir;
    private static FileRW fileRW;

    public static void MainFrameApp() {
        MainFrameApp.fileRW = new FileRW();
        MainFrameApp.recordList = new ArrayList();
        MainFrameApp.templateList = new ArrayList();
        for (int i = 0; i < tabCount; i++) {
            templateList.add(new ArrayList());
        }
    }

    public static void readyToSave(int templateIndex) {
        MainFrameApp.dir = (String) MainFrameApp.dirList.get(templateIndex);
        MainFrameApp.recordList.clear();
        ArrayList formatRecords = applyTemplateFormater(templateIndex, (ArrayList) templateList.get(templateIndex));
        MainFrameApp.recordList.addAll(formatRecords);
    }

    private static ArrayList applyTemplateFormater(int templateIndex, ArrayList list) {
        if (templateIndex == 0) {
            return MailingTemplate.formatRecords(list);
        } else if (templateIndex == 1) {
            return IdentityTemplate.formatRecords(list);
        } else {
            return null;
        }
    }

    public static boolean saveFile(boolean flag) throws IOException {
        if (!flag) {
            return flag;
        }
        if (null != recordList) {
            try {
                MainFrameApp.fileRW.writeFile(dir);
                for (int i = 0; i < recordList.size(); i++) {
                    MainFrameApp.fileRW.getWriter().println(recordList.get(i).toString());
                }
                return true;

            } catch (IOException ex) {
                Logger.getLogger(MainFrameApp.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("file not found error");
                return false;
            } finally {
                MainFrameApp.fileRW.closeWriter();
                clearRecordList();
            }
        }
        else
        {
            return false;
        }
    }

    public static String getDirList(int index) {
        return dirList.get(index);
    }

    public static ArrayList<ArrayList> getTemplateList() {
        return templateList;
    }

    public static void setTemplateList(ArrayList<ArrayList> templateList) {
        MainFrameApp.templateList = templateList;
    }

    public static int getTabCount() {
        return tabCount;
    }

    public static void setTabCount(int tabCount) {
        MainFrameApp.tabCount = tabCount;
    }

    public static FileRW getFileRW() {
        return fileRW;
    }

    public static void setFileRW(FileRW fileRW) {
        MainFrameApp.fileRW = fileRW;
    }

    public static String getDir() {
        return dir;
    }

    public static void setDir(String dir) {
        MainFrameApp.dir = dir;
    }
    private static final int MAX_COUNT = 1000;

    public static ArrayList getRecordList() {
        return recordList;
    }

    public static void setRecordList(ArrayList recordList) {
        MainFrameApp.recordList = recordList;
    }

    private static void clearRecordList() {
        MainFrameApp.recordList.clear();
    }

    public static void clearRecordListByTemplate(int index) {
        MainFrameApp.templateList.get(index).clear();
        MainFrameApp.clearRecordList();
    }

    public static int addRecord(JSONObject o, int selectedIndex) {
        templateList.get(selectedIndex).add(o);
        return templateList.get(selectedIndex).size();
    }

}
