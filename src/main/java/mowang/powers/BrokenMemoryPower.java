package mowang.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import mowang.Helpers.ModHelper;

public class BrokenMemoryPower extends AbstractPower {
	// 能力的ID
	public static final String POWER_ID = ModHelper.MakePath(BrokenMemoryPower.class.getSimpleName());
	// 能力的本地化字段
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	// 能力的名称
	private static final String NAME = powerStrings.NAME;
	// 能力的描述
	private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	private static final String PATH128 = "ModExampleResources/img/powers/Example84.png";
	private static final String PATH48 = "ModExampleResources/img/powers/Example32.png";

	private static int counter;
	
	public BrokenMemoryPower(AbstractCreature owner, int amt) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.type = PowerType.BUFF;

		// 如果需要不能叠加的能力，只需将上面的Amount参数删掉，并把下面的Amount改成-1就行
		this.amount = amt;
		
		// 添加一大一小两张能力图
		String path128 = PATH128;
		String path48 = PATH48;
		this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
		this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

		// 首次添加能力更新描述
		this.updateDescription();
	}

	public void checkDiscardPile() {
		int temp = AbstractDungeon.player.discardPile.size();
		if (temp < counter) {
			flash();
			AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.amount));
		} 
		if (temp != counter)
			counter = temp; 
	}

	@Override
	public void onDrawOrDiscard() {
		checkDiscardPile();
	}

	@Override
	public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
		checkDiscardPile();
	}

	@Override
	public void onInitialApplication() {
		checkDiscardPile();
	}

	@Override
	public void atEndOfRound() {
		checkDiscardPile();
	}

	@Override
	public void onAfterUseCard(AbstractCard card, UseCardAction action) {
		checkDiscardPile();
	}

	@Override
	public void atStartOfTurnPostDraw() {
		checkDiscardPile();
	}



	public void updateDescription() {
		this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
	}
}