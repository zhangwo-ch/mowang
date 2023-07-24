package mowang.Cards.Skill;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Action.GiveAllEnemyServitorAction;
import mowang.Action.ReplaceBurnAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

import java.util.Iterator;

import static mowang.Characters.MyCharacter.Enums.Recovery;

public class FlamePact extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(FlamePact.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public FlamePact() {
        super(ID, FlamePact.class.getSimpleName(),
                cardStrings, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        setupMagicNumber(3);
        tags.add(Recovery);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GiveAllEnemyServitorAction(3));
        int count = 0;
        Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var4.hasNext()) {
            AbstractMonster m2 = (AbstractMonster)var4.next();
            if (!m2.isDeadOrEscaped()) {
                ++count;
                this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }
        }
        addToBot(new MakeTempCardInDiscardAction(new Burn(), count));
    }
    @Override
    public void update() {
        super.update();
        ModHelper.GetMostLeftCard();
    }
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeMagicNumber(1);
    }
}