package ru.itprogram.service.converter.promocode;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dao.PromoCode;
import ru.itprogram.entity.dto.PromoCodeDto;
import ru.itprogram.service.converter.Converter;
import ru.itprogram.utils.generater.dto.PromoCodeDtoGenerate;

public class PromoCodeToPromoCodeDto implements Converter<PromoCodeDto, PromoCode> {
    @Autowired
    private PromoCodeDtoGenerate promoCodeDtoGenerate;

    @Override
    public PromoCodeDto conversion(PromoCode promoCode) {
        PromoCodeDto promoCodeDto = promoCodeDtoGenerate.getPromoCodeDto();
        promoCodeDto.setCode(promoCode.getCode());
        promoCodeDto.setDiscount(promoCode.getDiscount());
        return promoCodeDto;
    }
}
