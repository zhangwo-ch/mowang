package mowang.Cards.Skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Action.GiveAllEnemyServitorAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;
import mowang.powers.ServitorPower;

public class EternalConnection extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(EternalConnection.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public EternalConnection() {
        super(ID, EternalConnection.class.getSimpleName(),
                cardStrings, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        setupMagicNumber(6);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ScryAction(this.magicNumber));
        if (ModHelper.CanReturn(8)){
            gainBlock(p.maxHealth-p.currentHealth);
        }
        if (ModHelper.CanReturn(12)){
            this.addToBot(new GiveAllEnemyServitorAction(magicNumber));
        }
    }
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeMagicNumber(2);
    }
}