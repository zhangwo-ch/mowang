package mowang.Cards.Attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Action.MarchAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;
import static mowang.Characters.MyCharacter.Enums.Recovery;

public class Deviflame extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(Deviflame.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public Deviflame() {
        super(ID, Deviflame.class.getSimpleName(),
                cardStrings, 2, ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.setupDamage(15);
        isMultiDamage = true;
        tags.add(Recovery);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damageToAllEnemies(AbstractGameAction.AttackEffect.FIRE);
    }

    @Override
    public void update() {
        super.update();
        ModHelper.GetMostLeftCard();
    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(5); // 将该卡牌的伤害提高3点。
    }
}
