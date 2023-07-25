package mowang.Cards.Skill;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Cards.AbstractExampleCard;
import mowang.Cards.AbstractHealCard;
import mowang.Helpers.ModHelper;

import static mowang.Characters.MyCharacter.Enums.Recovery;

public class WraithLantern extends AbstractHealCard {
    public static final String ID = ModHelper.MakePath(WraithLantern.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public WraithLantern() {
        super(ID, WraithLantern.class.getSimpleName(),
                cardStrings, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE, new AbstractGameAction() {
                    @Override
                    public void update() {
                        addToBot(new GainEnergyAction(1));
                        addToBot(new MakeTempCardInHandAction(new Lantern(),1));
                        isDone = true;
                    }
                });
        setupMagicNumber(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ScryAction(this.magicNumber));
        this.addToBot(new MakeTempCardInDiscardAction(new Lantern(),magicNumber-2));
    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeMagicNumber(1);
    }
}