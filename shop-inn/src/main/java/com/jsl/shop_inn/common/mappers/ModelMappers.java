package com.jsl.shop_inn.common.mappers;

import com.jsl.shop_inn.common.util.DescriptionResponse;
import com.jsl.shop_inn.common.util.ItemResponse;
import com.jsl.shop_inn.file.FileUtils;
import com.jsl.shop_inn.models.Furniture;
import com.jsl.shop_inn.models.FurnitureImageNames;

import java.util.ArrayList;
import java.util.List;

public class ModelMappers {
    public static ItemResponse toItemResponse(Furniture furniture) {
        List<FurnitureImageNames> furnitureImageNames = new ArrayList<>();
        if (furniture.getFurnitureImageNames() == null) furniture.setFurnitureImageNames(furnitureImageNames);
        return ItemResponse.builder()
                .id(furniture.getId())
                .descriptionResponse(
                        new DescriptionResponse(
                                furniture.getDescription().getColor(),
                                furniture.getDescription().getDimensions(),
                                furniture.getDescription().getCategory().name(),
                                furniture.getDescription().getAdditional()
                        )
                )
                .files(FileUtils.readFiles(furniture.getFurnitureImageNames()))
                .price(furniture.getPrice())
                .name(furniture.getName())
                .build();
    }
}
