/**
 * 이 컴포넌트는 item 프로퍼티로 부모 컴포넌트의 데이터를 받을 수 있다.
 *
 *
 */

import lifecycleLogger from '../mixins/lifecycle-logger.mixin.js';

export default {
	name : 'MessageListItem',
	mixins : [lifecycleLogger],
	template : `<li>
					{{ item.text }} - {{ item.inputDate | datetime('MM/DD/YYYY') }}
					<button @click="deleteClicked">X</button>
				</li>`
	,
	props : {
		item : {
			type : Object,
			required : true,
		}
	},
	methods : {
		deleteClicked() {
			this.$emit('delete');
		}
	}
}

