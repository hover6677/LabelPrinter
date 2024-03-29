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
public class MailingTemplate {

    private static ArrayList list;
    private static final String ID = "ID";
    private static final String FirstName = "FirstName";
    private static final String Surname = "Surname";
    private static final String Address = "Address";
    private static final String Country = "Country";
    private static final String ShortForm = "ShortForm";
    private static final String Postcode = "Postcode";

    public static ArrayList formatRecords(ArrayList<JSONObject> jsonlist) {
        list = new ArrayList();
        if (null == jsonlist) {
            return list;
        } else {
            for (int i = 0; i < jsonlist.size(); i++) {
                String line = "";
                try {
                    JSONObject record = jsonlist.get(i);
                    line += record.getString(ID);
                    line += "," + record.getString(FirstName);
                    line += ", " + record.getString(Surname);
                    line += ",\t\"";
                    line += record.getString(Address);
                    line += "\", ";
                    line += record.getString(Country)+",";
                    line += record.getString(ShortForm)+",";
                    line += record.getString(Postcode);
                    
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
