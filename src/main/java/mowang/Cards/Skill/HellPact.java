package mowang.Cards.Skill;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Action.ReplaceBurnAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

public class HellPact extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(HellPact.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public HellPact() {
        super(ID, HellPact.class.getSimpleName(),
                cardStrings, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        setupMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainEnergyAction(magicNumber));
        this.addToBot(new ReplaceBurnAction(1));
    }
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeMagicNumber(1);
    }
}