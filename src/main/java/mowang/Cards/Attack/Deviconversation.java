package mowang.Cards.Attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Action.MarchAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;
import mowang.powers.ServitorPower;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class Deviconversation extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(Deviconversation.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public Deviconversation() {
        super(ID, Deviconversation.class.getSimpleName(),
                cardStrings, 1, ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.setupDamage(8);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damageToEnemy(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        if (p.hasPower(ServitorPower.POWER_ID)){
            if (p.getPower(ServitorPower.POWER_ID).amount>=3){
                damageToEnemy(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
                this.addToBot(new ReducePowerAction(p,p,ServitorPower.POWER_ID,3));
            }
        }

    }
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(ServitorPower.POWER_ID)){
            if (p.getPower(ServitorPower.POWER_ID).amount>=3){
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            }
        }
    }
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(3);
    }
}
