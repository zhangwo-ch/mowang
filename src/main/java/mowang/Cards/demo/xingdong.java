package mowang.Cards.demo;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Action.xingdongAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class xingdong extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(xingdong.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public xingdong() {
        super(ID, xingdong.class.getSimpleName(),
                cardStrings, 1, ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new xingdongAction());
    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
    }
}
