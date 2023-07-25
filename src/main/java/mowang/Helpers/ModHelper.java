package mowang.Helpers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import mowang.Cards.AbstractHealCard;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

import static basemod.BaseMod.logger;
import static basemod.DevConsole.log;
import static mowang.Characters.MyCharacter.Enums.Recovery;
import static mowang.Characters.MyCharacter.Enums.SIB;

public class ModHelper {
    public static Boolean hasReturn;
    public static int ReturnCount = 0;
    public static String MakePath(String id) {
        return "mowang:" + id;
    }

    public static String MakeAssetPath() {
        String IMG_PATH = "ModExampleResources/img/cards/Strike.png";

        return IMG_PATH;
    }

    public static Boolean CanReturn(int magicNumber, AbstractGameAction action) {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.discardPile.group.size() >= magicNumber){
            hasReturn = true;
            ReturnCount++;
            AbstractDungeon.actionManager.addToBottom(action);
            return true;
        }
        return false;
    }

    // 注：此方法只用作是否变色的判断
    public static Boolean CanReturn(int magicNumber) {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.discardPile.group.size() >= magicNumber){
            return true;
        }
        return false;
    }
    public static Boolean HasReturn() {
        return hasReturn;
    }

    //打印所有手牌
    public static AbstractCard GetMostLeftCard() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p == null){
            return null;
        }
        if (p.hand == null){
            return null;
        }
        if (p.hand.group == null){
            return null;
        }
        ArrayList<AbstractCard> group = p.hand.group;
        for (AbstractCard c : group) {
            if (c.hasTag(Recovery)) {
                AbstractHealCard Rcard = (AbstractHealCard) c;
                if (Rcard.hasRecovery){
                    logger.info(c.name+"已自愈！！");
                    continue;
                }
                logger.info(c.name);
                return c;
            }
        }

        logger.info("没有自愈牌，你为什么在获取最左的卡牌？？");
        return null;
    }
    public static AbstractCard GetMostLeftState() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p == null){
            return null;
        }
        if (p.hand == null){
            return null;
        }
        if (p.hand.group == null){
            return null;
        }
        ArrayList<AbstractCard> group = p.hand.group;
        for (AbstractCard c : group) {
            if (c.type== AbstractCard.CardType.STATUS) {
                logger.info(c.name);
                return c;
            }
        }

        logger.info("没有状态牌");
        return null;
    }

    public static AbstractCard GetMostLeftCard(boolean t2heal_f2status) {
        for(AbstractCard c : AbstractDungeon.player.hand.group) {
            if((t2heal_f2status && c.hasTag(Recovery)) ||
                    (!t2heal_f2status && c.type == AbstractCard.CardType.STATUS && !c.hasTag(SIB))) {
                return c;
            }
        }
        return null;
    }
}
