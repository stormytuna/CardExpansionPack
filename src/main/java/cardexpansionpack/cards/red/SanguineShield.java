package cardexpansionpack.cards.red;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class SanguineShield extends BaseCard {
    public static final String ID = makeID("SanguineShield");

    private static final CardStats INFO = new CardStats(
        CardColor.RED,
        CardType.SKILL,
        CardRarity.COMMON,
        CardTarget.SELF,
        1
    );

    private static final int BLOCK = 12;
    private static final int BLOCK_UPGRADE = 4;
    private static final int HP_LOSS = 2;

    public SanguineShield() {
        super(ID, INFO);
        setBlock(BLOCK, BLOCK_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new SanguineShield();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new DamageAction(p, new DamageInfo(p, HP_LOSS, DamageType.HP_LOSS), AttackEffect.FIRE));
    }
}

