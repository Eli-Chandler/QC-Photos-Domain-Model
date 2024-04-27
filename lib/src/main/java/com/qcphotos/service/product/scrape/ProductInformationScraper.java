package com.qcphotos.service.product.scrape;

import com.qcphotos.model.ProductIdentifier;
import com.qcphotos.service.product.ProductInformation;

public interface ProductInformationScraper {
    ProductInformation scrapeProductInformation(ProductIdentifier productIdentifier);
}
