package mowang.Helpers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ModHelper {
    public static String MakePath(String id) {
        return "mowang:" + id;
    }

    public static String MakeAssetPath() {
        String IMG_PATH = "ModExampleResources/img/cards/Strike.png";

        return IMG_PATH;
    }
}
