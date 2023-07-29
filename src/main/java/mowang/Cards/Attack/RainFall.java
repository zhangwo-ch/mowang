package mowang.Cards.Attack;

import mowang.Action.RainFallAction;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class RainFall extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(RainFall.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public RainFall() {
        super(ID, RainFall.class.getSimpleName(),
                cardStrings, 1, ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = 12;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    	AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RainFallAction());
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(4);
    }
}
