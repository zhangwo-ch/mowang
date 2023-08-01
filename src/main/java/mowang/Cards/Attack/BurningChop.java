package mowang.Cards.Attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Action.ActionAction;
import mowang.Action.FetchAllBurnFromDisCardPileAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class BurningChop extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(BurningChop.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public BurningChop() {
        super(ID, BurningChop.class.getSimpleName(),
                cardStrings, 1, ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.setupDamage(7);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damageToEnemy(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        damageToEnemy(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        addToBot(new MakeTempCardInDiscardAction(new Burn(),2));
        ModHelper.CanReturn(10,new FetchAllBurnFromDisCardPileAction());
    }
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(2); // 将该卡牌的伤害提高3点。
    }
}
