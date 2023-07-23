package mowang.Helpers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

import static basemod.BaseMod.logger;
import static basemod.DevConsole.log;

public class ModHelper {
    public static Boolean hasReturn;
    public static String MakePath(String id) {
        return "mowang:" + id;
    }

    public static String MakeAssetPath() {
        String IMG_PATH = "ModExampleResources/img/cards/Strike.png";

        return IMG_PATH;
    }

    public static Boolean CanReturn(int magicNumber) {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.discardPile.group.size() >= magicNumber){
            hasReturn = true;
            return true;
        }
        return false;
    }

    //打印所有手牌
    public static AbstractCard GetMostLeftCard() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p == null){
            return null;
        }
        Iterator<AbstractCard> var = p.hand.group.iterator();
        while (var.hasNext()) {
            AbstractCard c = var.next();
            logger.info(c.name + ";" + c.current_x + ";" + c.current_y);
        }
        return null;
    }
    public static Boolean HasReturn() {
        return hasReturn;
    }
}
