package ru.itprogram.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dto.BrandDto;
import ru.itprogram.entity.dto.ProductDto;
import ru.itprogram.entity.dto.ProductTypeDto;
import ru.itprogram.entity.dto.PromoCodeDto;
import ru.itprogram.service.ProductService;
import ru.itprogram.utils.generater.ArrayListGenerate;
import ru.itprogram.utils.generater.FileReaderGenerate;
import ru.itprogram.utils.generater.dto.BrandDtoGenerate;
import ru.itprogram.utils.generater.dto.ProductDtoGenerate;
import ru.itprogram.utils.generater.dto.ProductTypeDtoGenerate;
import ru.itprogram.utils.generater.dto.PromoCodeDtoGenerate;

import java.io.FileReader;
import java.util.List;

public class ReadProductInFile {
    public static final String PATH = "ProductList";
    private static final String TRANSITION_TO_A_NEW_LINE = "\n";
    private static final String BY_STRAIGHT_LINE = "\\|";
    private static Logger logger;
    @Autowired
    private StringBuilder stringBuilder;
    @Autowired
    private FileReaderGenerate fileReaderGenerate;
    @Autowired
    private ArrayListGenerate arrayListGenerate;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDtoGenerate productDtoGenerate;
    @Autowired
    private BrandDtoGenerate brandDtoGenerate;
    @Autowired
    private ProductTypeDtoGenerate productTypeDtoGenerate;
    @Autowired
    private PromoCodeDtoGenerate promoCodeDtoGenerate;

    public ReadProductInFile() {
        logger = LoggerFactory.getLogger(ReadProductInFile.class);
    }

    public void readFile(String pathFile) {
        FileReader fileReader = null;
        try {
            fileReader = fileReaderGenerate.getFileReader();
            int c;
            while ((c = fileReader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            fileReader.close();
            writeDb();
        } catch (java.io.IOException e) {
            logger.warn(e.getMessage(), e);
        }
    }

    private void writeDb() {
        List<String> strings = arrayListGenerate.getArrayList();
        for (String s : stringBuilder.toString().split(TRANSITION_TO_A_NEW_LINE)) {
            strings.add(s);
        }
        for (String s : strings) {
            BrandDto brandDto = brandDtoGenerate.getBrandDto();
            brandDto.setName(s.split(BY_STRAIGHT_LINE)[0]);
            ProductTypeDto productTypeDto = productTypeDtoGenerate.getProductTypeDto();
            productTypeDto.setType(s.split(BY_STRAIGHT_LINE)[1]);
            PromoCodeDto promoCodeDto = promoCodeDtoGenerate.getPromoCodeDto();
            promoCodeDto.setCode(s.split(BY_STRAIGHT_LINE)[7]);
            promoCodeDto.setDiscount(Short.valueOf(s.split(BY_STRAIGHT_LINE)[8]));
            ProductDto productDto = productDtoGenerate.getProductDto();
            productDto.setId(0);
            productDto.setBrandDto(brandDto);
            productDto.setProductTypeDto(productTypeDto);
            productDto.setDescription(s.split(BY_STRAIGHT_LINE)[2]);
            productDto.setQuantity(Integer.valueOf(s.split(BY_STRAIGHT_LINE)[3]));
            productDto.setWarranty(Short.valueOf(s.split(BY_STRAIGHT_LINE)[4]));
            productDto.setAvailable(Boolean.valueOf(s.split(BY_STRAIGHT_LINE)[5]));
            productDto.setPrice(Double.valueOf(s.split(BY_STRAIGHT_LINE)[6]));
            productDto.setPromoCodeDto(promoCodeDto);
            productService.add(productDto);
        }
    }
}
