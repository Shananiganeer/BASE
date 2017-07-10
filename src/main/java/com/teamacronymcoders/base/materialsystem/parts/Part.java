package com.teamacronymcoders.base.materialsystem.parts;

import com.teamacronymcoders.base.materialsystem.parttype.PartType;
import com.teamacronymcoders.base.util.TextUtils;

import java.util.Locale;

public class Part {
    private String name;
    private String unlocalizedName;
    private String oreDictName;
    private PartType partType;
    private String textureName;

    Part(String name, String unlocalizedName, String textureName, PartType partType) {
        this.name = name;
        this.unlocalizedName = unlocalizedName;
        this.textureName = textureName;
        String oreDict = name.substring(0, 1).toLowerCase(Locale.US) + name.substring(1);
        this.oreDictName = TextUtils.removeSpecialCharacters(oreDict);
        this.partType = partType;
    }

    Part(String name, String unlocalizedName, PartType partType) {
        this(name, unlocalizedName, TextUtils.toSnakeCase(name), partType);
    }

    public String getName() {
        return name;
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public PartType getPartType() {
        return partType;
    }

    public String getPartTypeName() {
        return partType.getName();
    }

    public String getOreDictPrefix() {
        return this.oreDictName;
    }

    public String getTextureName() {
        return textureName;
    }
}
