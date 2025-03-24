package com.ttn.project.core.models.impl;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.ttn.project.core.models.MultifieldModel;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model(
        adaptables = Resource.class,
        adapters = MultifieldModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultifieldModelImpl implements MultifieldModel {

    // Injecting the current component resource
    @SlingObject
    private Resource componentResource;

    @SlingObject
    ResourceResolver resolver;


    @Override
    public List<String> getBlogTitle() {

        List<String> BlogTitleList = new ArrayList<>();

        // Retrieve the 'actions' child resource under the current component
        Resource linksResource = componentResource.getChild("actions");

        if (linksResource != null) {
            for (Resource item : linksResource.getChildren()) {

               ValueMap valueMap = item.getValueMap();
               String title = valueMap.get("jcr:title", String.class);
                BlogTitleList.add(title);
            }
        }
        return BlogTitleList;
    }
git
}


