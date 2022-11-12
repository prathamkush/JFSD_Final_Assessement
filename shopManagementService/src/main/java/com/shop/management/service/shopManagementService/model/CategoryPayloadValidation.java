
package com.shop.management.service.shopManagementService.model;


import java.util.Map;

public class CategoryPayloadValidation {

    public static boolean createdPayloadIdValidation(Category category){


        if(category.getCategory_id()>0){
            return false;
        }

        return true;
    }

    public static boolean createdPayloadDescriptionField(Map<String, Object> map){

        if(!map.containsKey("description")) return false;
        return true;
    }


}
