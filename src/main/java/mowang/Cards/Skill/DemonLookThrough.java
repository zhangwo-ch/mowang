package mowang.Cards.Skill;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import mowang.Action.DemonLookAction;
import mowang.Action.GiveAllEnemyServitorAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

import java.util.Iterator;

public class DemonLookThrough extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(DemonLookThrough.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public DemonLookThrough() {
        super(ID, DemonLookThrough.class.getSimpleName(),
                cardStrings, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        setupMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DemonLookAction(m));
        if (ModHelper.CanReturn(13)){
            Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

            while(var3.hasNext()) {
                AbstractMonster mo = (AbstractMonster)var3.next();
                this.addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
        if (ModHelper.CanReturn(23)){
            applyToPlayer(new StrengthPower(p,magicNumber));
        }
    }
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeMagicNumber(1);
    }
}