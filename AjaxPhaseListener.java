/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.bam.mvcbeans;

import com.newgen.customreport.session.WFSession;
import java.io.Serializable;
import java.util.Locale;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author rajeshwar.rudra
 */
public class AjaxPhaseListener  implements PhaseListener,Serializable {

    @Override
    public void afterPhase(PhaseEvent pe) {
        FacesContext context = FacesContext.getCurrentInstance();
        WFSession wfSession = (WFSession) FacesContext.getCurrentInstance().getApplication().createValueBinding("#{crSession}").getValue(FacesContext.getCurrentInstance());
        String selectedLocale = wfSession.getM_strSelectedLocale();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
//        if (selectedLocale != "" && selectedLocale != null) {
          if (selectedLocale != null && !selectedLocale.equals("")) {
             Locale locale;
            if(selectedLocale.split("_").length > 1){
                String localeStr = selectedLocale.split("_")[0]; 
                String countryStr = selectedLocale.split("_")[selectedLocale.split("_").length-1];
                locale = new Locale(localeStr, countryStr);
            }else if(selectedLocale.split("-").length > 1){
                String localeStr = selectedLocale.split("-")[0]; 
                String countryStr = selectedLocale.split("-")[selectedLocale.split("-").length-1];
                locale = new Locale(localeStr, countryStr);
            }else{
                locale = new Locale(selectedLocale);
            }
            FacesContext.getCurrentInstance().getViewRoot().setLocale(locale); 
        }else{
            
            FacesContext.getCurrentInstance().getViewRoot().setLocale(request.getLocale());
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}
