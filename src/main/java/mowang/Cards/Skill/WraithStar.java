package mowang.Cards.Skill;

import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

public class WraithStar extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(WraithStar.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public WraithStar() {
        super(ID, WraithStar.class.getSimpleName(),
                cardStrings, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        setupBlock(8);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        gainBlock();
        if (ModHelper.CanReturn(24)){
            this.addToBot(new BetterDiscardPileToHandAction(1,0));
        }
        else if (ModHelper.CanReturn(8)) {
            this.addToBot(new BetterDiscardPileToHandAction(1));
        }
    }
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeBlock(4);
    }
}