package ru.itprogram.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itprogram.entity.dao.PromoCode;
import ru.itprogram.entity.dto.PromoCodeDto;
import ru.itprogram.repository.PromoCodeRepository;
import ru.itprogram.service.converter.promocode.PromoCodeDtoToPromoCode;
import ru.itprogram.service.converter.promocode.PromoCodeToPromoCodeDto;
import ru.itprogram.utils.generater.ArrayListGenerate;

import java.util.List;

public class PromoCodeService implements Service<PromoCodeDto> {
    @Autowired
    private PromoCodeRepository promoCodeRepository;
    @Autowired
    private ArrayListGenerate arrayListGenerate;
    @Autowired
    private PromoCodeToPromoCodeDto promoCodeToPromoCodeDto;
    @Autowired
    private PromoCodeDtoToPromoCode promoCodeDtoToPromoCode;

    @Override
    public List<PromoCodeDto> getAll() {
        List<PromoCode> promoCodes = promoCodeRepository.getAllEntity();
                List<PromoCodeDto> promoCodeDtoList = arrayListGenerate.getArrayList();
        for (PromoCode promoCode : promoCodes) {
            promoCodeDtoList.add(promoCodeToPromoCodeDto.conversion(promoCode));
        }
        return promoCodeDtoList;
    }

    @Override
    public void add(PromoCodeDto promoCodeDto) {
        new PromoCodeRepository().saveEntity(
                promoCodeDtoToPromoCode.conversion(promoCodeDto));
    }
}
