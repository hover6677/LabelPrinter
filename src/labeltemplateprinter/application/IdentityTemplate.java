/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labeltemplateprinter.application;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author admin1
 */
public class IdentityTemplate {

    private static ArrayList list;
    private static final String Code = "Code";
    private static final String Client = "Client";
    private static final String City = "City";
    private static final String Address = "Address";
    private static final String Zip = "Zip";
    private static final String LineDilimiter = "+--------------------------------+";

    public static ArrayList formatRecords(ArrayList<JSONObject> jsonlist) {
       list = new ArrayList();
        if (null == jsonlist) {
            return list;
        } else {
            for (int i = 0; i < jsonlist.size(); i++) {
                String line = "";
                try {
                    JSONObject record = jsonlist.get(i);
                    line += LineDilimiter+"\n";
                    line += "|"+Code+": "+record.getString(Code)+"\n";
                    line += "|   "+Client+":  "+ record.getString(Client)+"\n";
                    line += "|   "+Address+": "+ record.getString(Address)+"\n";
                    line += "|   "+City+":    "+ record.getString(City)+"\n";
                    line += "|   "+Zip+":     "+ record.getString(Zip);
                    
                } catch (JSONException ex) {
                    Logger.getLogger(MailingTemplate.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Decode Mailing error");
                    continue;
                }
                list.add(line);
            }
        }
        return list;
    }
}
