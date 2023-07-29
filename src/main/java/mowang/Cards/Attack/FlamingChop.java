package mowang.Cards.Attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import mowang.Action.AddLanternAction;
import mowang.Action.EmptyAction;
import mowang.Cards.AbstractHealCard;
import mowang.Cards.Skill.Lantern;
import mowang.Helpers.ModHelper;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

import java.util.Iterator;
import java.util.Objects;

public class FlamingChop extends AbstractHealCard {
    public static final String ID = ModHelper.MakePath(FlamingChop.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public FlamingChop() {
        super(ID, FlamingChop.class.getSimpleName(),
                cardStrings, 1, ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, null);
        this.damage = this.baseDamage = 4;
        this.magicNumber = this.baseMagicNumber = 2;
        action = new AddLanternAction(this,true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i=0;i<2;i++)
            addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

        Iterator<AbstractCard> var5 = p.discardPile.group.iterator();
        int tmp=0;
        while (var5.hasNext()) {
            if(var5.next().cardID.equals("Burn")) {
                tmp+=1;
            }
        }
        if(tmp>0)
            addToBot(new ScryAction(tmp));
    }
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(2);
        this.upgradeMagicNumber(1);
    }
}
