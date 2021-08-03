/**
 * 이 파일은 ES6 모듈이다.
 * 컴포넌트를 정의하는 options 객체를 기본 내보내기로 내보낸다.
 * name 프로퍼티로 이 프로퍼티 이름을 추가했다.
 * template 프로퍼티는 index.html 의 ul 객체와 그 내부 모두를 인라인 템플릿으로 복사한다.
 * methods 에서는 $emit() 으로 MessageList 의 delete 이벤트를 트리거 하기 때문에
 * 부모 컴포넌트에서는 @delete="..." 으로 이 이벤트를 수신할 수 있다.
 *
 * props 프로퍼티를 정의하는 부분에서는 배열 대신 객체를 사용해서 Vue.js 에 필요한 데이터 구조에 대한 세부 정보를 전달한다.
 * 만약 이 때 items 를 Array 로 선언했고, required 필수로 선언했는데 배열이 아닌 문자열이나 다른 값을 전달받으면
 * Vue.js 는 에러를 던진다.
 */
import MessageListItem from "./MessageListItem.js";
import lifecycleLogger from '../mixins/lifecycle-logger.mixin.js';
export default {
	name : 'MessageList',
	mixins : [lifecycleLogger],
	template : `<ul>
					<message-list-item v-for="item in items"
										:item="item"
										:key="item.id"
										@delete="deleteMessage(item)">
					</message-list-item>
				</ul>`,
	props : {
		items : {
			type : Array,
			required : true,
		},
	},
	components : {
		MessageListItem
	},
	methods : {
		deleteMessage( item ) {
			this.$emit('delete', item );
		}
	},
}

